/*
 * $Id: CollectionSerializerWriter.java,v 1.2 2006-04-13 01:29:10 ofung Exp $
 */

/*
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the License).  You may not use this file except in
 * compliance with the License.
 * 
 * You can obtain a copy of the license at
 * https://glassfish.dev.java.net/public/CDDLv1.0.html.
 * See the License for the specific language governing
 * permissions and limitations under the License.
 * 
 * When distributing Covered Code, include this CDDL
 * Header Notice in each file and include the License file
 * at https://glassfish.dev.java.net/public/CDDLv1.0.html.
 * If applicable, add the following below the CDDL Header,
 * with the fields enclosed by brackets [] replaced by
 * you own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 * 
 * Copyright 2006 Sun Microsystems Inc. All Rights Reserved
 */

package com.sun.xml.rpc.processor.generator.writer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import com.sun.xml.rpc.processor.generator.GeneratorConstants;
import com.sun.xml.rpc.processor.generator.Names;
import com.sun.xml.rpc.processor.model.AbstractType;
import com.sun.xml.rpc.processor.model.soap.SOAPArrayType;
import com.sun.xml.rpc.processor.model.soap.SOAPSimpleType;
import com.sun.xml.rpc.processor.model.soap.SOAPType;
import com.sun.xml.rpc.processor.util.IndentingWriter;
import com.sun.xml.rpc.wsdl.document.schema.SchemaConstants;

/**
 *
 * @author JAX-RPC Development Team
 */
public class CollectionSerializerWriter
    extends SerializerWriterBase
    implements GeneratorConstants {
    private String serializerMemberName;
    private CollectionInfo collectionInfo;
    private SOAPType dataType;

    public CollectionSerializerWriter(SOAPType type, Names names) {
        super(type, names);
        dataType = type;

        collectionInfo = (CollectionInfo) collectionTypes.get(type.getName());
        String serializerName = collectionInfo.serializerName;
        // changed this to avoid naming conflicts when two collection-valued properties
        // are declared in a value object
        // serializerMemberName = Names.getClassMemberName(serializerName);
        serializerMemberName = names.getClassMemberName(serializerName, type);
    }

    public void createSerializer(
        IndentingWriter p,
        StringBuffer typeName,
        String serName,
        boolean encodeTypes,
        boolean multiRefEncoding,
        String typeMapping)
        throws IOException {
        SOAPSimpleType type = (SOAPSimpleType) this.type;
        String nillable = (type.isNillable() ? NULLABLE_STR : NOT_NULLABLE_STR);
        String referenceable =
            (type.isReferenceable()
                ? REFERENCEABLE_STR
                : NOT_REFERENCEABLE_STR);
        String multiRef =
            (multiRefEncoding
                && type.isReferenceable()
                    ? SERIALIZE_AS_REF_STR
                    : DONT_SERIALIZE_AS_REF_STR);
        String encodeType =
            (encodeTypes ? ENCODE_TYPE_STR : DONT_ENCODE_TYPE_STR);

        declareType(p, typeName, type.getName(), false, false);
        if (type.getName().equals(QNAME_TYPE_COLLECTION)
            || type.getName().equals(QNAME_TYPE_LIST)
            || type.getName().equals(QNAME_TYPE_SET)
            || type.getName().equals(QNAME_TYPE_MAP)
            || type.getName().equals(QNAME_TYPE_JAX_RPC_MAP_ENTRY)) {
            p.plnI(
                serializerName()
                    + " "
                    + serName
                    + " = new "
                    + collectionInfo.serializerName
                    + "("
                    + typeName
                    + ",");
            p.pln(
                encodeType
                    + ", "
                    + nillable
                    + ", "
                    + getEncodingStyleString()
                    + ");");
            p.pO();
        } else if (
            type.getName().equals(QNAME_TYPE_HASH_MAP)
                || type.getName().equals(QNAME_TYPE_TREE_MAP)
                || type.getName().equals(QNAME_TYPE_HASHTABLE)
                || type.getName().equals(QNAME_TYPE_PROPERTIES)) {
            StringBuffer elemName = new StringBuffer("elemName");
            declareType(p, elemName, COLLECTION_ELEMENT_NAME, false, false);
            p.plnI(
                serializerName()
                    + " "
                    + serName
                    + " = new "
                    + collectionInfo.serializerName
                    + "("
                    + typeName
                    + ",");
            p.pln(
                collectionInfo.collectionClassName
                    + ".class, "
                    + encodeType
                    + ", "
                    + nillable
                    + ", "
                    + getEncodingStyleString()
                    + ", "
                    + getSOAPVersionString()
                    + ");");
            p.pO();
        } else {
            StringBuffer elemName = new StringBuffer("elemName");
            declareType(p, elemName, COLLECTION_ELEMENT_NAME, false, false);
            StringBuffer elemType = new StringBuffer("elemType");
            declareType(
                p,
                elemType,
                collectionInfo.elementTypeName,
                false,
                false);
            p.plnI(
                serializerName()
                    + " "
                    + serName
                    + " = new "
                    + collectionInfo.serializerName
                    + "("
                    + typeName
                    + ",");
            p.pln(
                collectionInfo.collectionClassName
                    + ".class, "
                    + encodeType
                    + ", "
                    + nillable
                    + ", "
                    + getEncodingStyleString()
                    + ", ");
            p.pln(
                elemName
                    + ", "
                    + elemType
                    + ", "
                    + OBJECT_CLASSNAME
                    + ".class, "
                    + getSOAPVersionString()
                    + ");");
            p.pO();
        }
        if (type.isReferenceable()) {
            p.plnI(
                serName
                    + " = new "
                    + REFERENCEABLE_SERIALIZER_NAME
                    + "("
                    + multiRef
                    + ", "
                    + serName
                    + ", "
                    + getSOAPVersionString()
                    + ");");
            p.pO();
        }
    }

    public void declareSerializer(
        IndentingWriter p,
        boolean isStatic,
        boolean isFinal)
        throws IOException {
        String modifier = getPrivateModifier(isStatic, isFinal);
        p.pln(modifier + serializerName() + " " + serializerMemberName() + ";");
    }

    public String serializerMemberName() {
        return getPrefix(dataType) + UNDERSCORE + serializerMemberName;
    }

    public String deserializerMemberName() {
        return getPrefix(dataType) + UNDERSCORE + serializerMemberName;
    }

    public static boolean handlesType(AbstractType type) {
        return collectionTypes.containsKey(type.getName());
    }

    protected String getPrivateModifier(boolean isStatic, boolean isFinal) {
        return "private " + super.getModifier(isStatic, isFinal);
    }

    public AbstractType getBaseElementType() {
        SOAPType elemType = ((SOAPArrayType) type).getElementType();
        while (elemType instanceof SOAPArrayType) {
            elemType = ((SOAPArrayType) elemType).getElementType();
        }
        return elemType;
    }

    private static final Map collectionTypes = new HashMap();

    // Collections
    public static final CollectionInfo COLLECTION_INFO =
        new CollectionInfo(
            COLLECTION_CLASSNAME,
            COLLECTION_INTERFACE_SERIALIZER_NAME,
            SchemaConstants.QNAME_TYPE_URTYPE);
    public static final CollectionInfo LIST_INFO =
        new CollectionInfo(
            LIST_CLASSNAME,
            COLLECTION_INTERFACE_SERIALIZER_NAME,
            SchemaConstants.QNAME_TYPE_URTYPE);
    public static final CollectionInfo SET_INFO =
        new CollectionInfo(
            SET_CLASSNAME,
            COLLECTION_INTERFACE_SERIALIZER_NAME,
            SchemaConstants.QNAME_TYPE_URTYPE);
    public static final CollectionInfo VECTOR_INFO =
        new CollectionInfo(
            VECTOR_CLASSNAME,
            COLLECTION_SERIALIZER_NAME,
            SchemaConstants.QNAME_TYPE_URTYPE);
    public static final CollectionInfo STACK_INFO =
        new CollectionInfo(
            STACK_CLASSNAME,
            COLLECTION_SERIALIZER_NAME,
            SchemaConstants.QNAME_TYPE_URTYPE);
    public static final CollectionInfo LINKED_LIST_INFO =
        new CollectionInfo(
            LINKED_LIST_CLASSNAME,
            COLLECTION_SERIALIZER_NAME,
            SchemaConstants.QNAME_TYPE_URTYPE);
    public static final CollectionInfo ARRAY_LIST_INFO =
        new CollectionInfo(
            ARRAY_LIST_CLASSNAME,
            COLLECTION_SERIALIZER_NAME,
            SchemaConstants.QNAME_TYPE_URTYPE);
    public static final CollectionInfo HASH_SET_INFO =
        new CollectionInfo(
            HASH_SET_CLASSNAME,
            COLLECTION_SERIALIZER_NAME,
            SchemaConstants.QNAME_TYPE_URTYPE);
    public static final CollectionInfo TREE_SET_INFO =
        new CollectionInfo(
            TREE_SET_CLASSNAME,
            COLLECTION_SERIALIZER_NAME,
            SchemaConstants.QNAME_TYPE_URTYPE);
    // Maps
    public static final CollectionInfo MAP_INFO =
        new CollectionInfo(
            MAP_CLASSNAME,
            MAP_INTERFACE_SERIALIZER_NAME,
            SchemaConstants.QNAME_TYPE_URTYPE);
    public static final CollectionInfo HASH_MAP_INFO =
        new CollectionInfo(HASH_MAP_CLASSNAME, MAP_SERIALIZER_NAME, null);
    public static final CollectionInfo TREE_MAP_INFO =
        new CollectionInfo(TREE_MAP_CLASSNAME, MAP_SERIALIZER_NAME, null);
    public static final CollectionInfo HASHTABLE_INFO =
        new CollectionInfo(HASHTABLE_CLASSNAME, MAP_SERIALIZER_NAME, null);
    public static final CollectionInfo PROPERTIES_INFO =
        new CollectionInfo(PROPERTIES_CLASSNAME, MAP_SERIALIZER_NAME, null);
    public static final CollectionInfo JAX_RPC_MAP_ENTRY_INFO =
        new CollectionInfo(
            JAX_RPC_MAP_ENTRY_CLASSNAME,
            JAX_RPC_MAP_ENTRY_SERIALIZER_NAME,
            null);

    static {
        // Collections
        collectionTypes.put(QNAME_TYPE_COLLECTION, COLLECTION_INFO);
        collectionTypes.put(QNAME_TYPE_LIST, LIST_INFO);
        collectionTypes.put(QNAME_TYPE_SET, SET_INFO);
        collectionTypes.put(QNAME_TYPE_VECTOR, VECTOR_INFO);
        collectionTypes.put(QNAME_TYPE_STACK, STACK_INFO);
        collectionTypes.put(QNAME_TYPE_LINKED_LIST, LINKED_LIST_INFO);
        collectionTypes.put(QNAME_TYPE_ARRAY_LIST, ARRAY_LIST_INFO);
        collectionTypes.put(QNAME_TYPE_HASH_SET, HASH_SET_INFO);
        collectionTypes.put(QNAME_TYPE_TREE_SET, TREE_SET_INFO);
        // Maps
        collectionTypes.put(QNAME_TYPE_MAP, MAP_INFO);
        collectionTypes.put(QNAME_TYPE_HASH_MAP, HASH_MAP_INFO);
        collectionTypes.put(QNAME_TYPE_TREE_MAP, TREE_MAP_INFO);
        collectionTypes.put(QNAME_TYPE_HASHTABLE, HASHTABLE_INFO);
        collectionTypes.put(QNAME_TYPE_PROPERTIES, PROPERTIES_INFO);
        collectionTypes.put(
            QNAME_TYPE_JAX_RPC_MAP_ENTRY,
            JAX_RPC_MAP_ENTRY_INFO);
    }

    private static class CollectionInfo {
        public String collectionClassName;
        public String serializerName;
        public QName elementTypeName;

        CollectionInfo(
            String collectionClassName,
            String serializerName,
            QName elementTypeName) {
            this.collectionClassName = collectionClassName;
            this.serializerName = serializerName;
            this.elementTypeName = elementTypeName;
        }
    }
}
