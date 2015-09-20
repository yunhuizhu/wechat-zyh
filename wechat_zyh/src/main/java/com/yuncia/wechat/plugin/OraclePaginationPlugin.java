package com.yuncia.wechat.plugin;

import java.util.List;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.Context;

public class OraclePaginationPlugin extends PluginAdapter
{
    public static String paginationOrcl = "com.cn21.mail189.analysis.util.PaginationOrcl";

    public boolean modelExampleClassGenerated(TopLevelClass paramTopLevelClass, IntrospectedTable paramIntrospectedTable)
    {
        addPage(paramTopLevelClass, paramIntrospectedTable, "page");
        return super.modelExampleClassGenerated(paramTopLevelClass, paramIntrospectedTable);
    }

    public boolean sqlMapDocumentGenerated(Document paramDocument, IntrospectedTable paramIntrospectedTable)
    {
        XmlElement localXmlElement1 = paramDocument.getRootElement();
        XmlElement localXmlElement2 = new XmlElement("sql");
        localXmlElement2.addAttribute(new Attribute("id", "OracleDialectPrefix"));
        XmlElement localXmlElement3 = new XmlElement("if");
        localXmlElement3.addAttribute(new Attribute("test", "page != null"));
        localXmlElement3.addElement(new TextElement("select * from ( select row_.*, rownum rownum_ from ( "));
        localXmlElement2.addElement(localXmlElement3);
        localXmlElement1.addElement(localXmlElement2);
        XmlElement localXmlElement4 = new XmlElement("sql");
        localXmlElement4.addAttribute(new Attribute("id", "OracleDialectSuffix"));
        XmlElement localXmlElement5 = new XmlElement("if");
        localXmlElement5.addAttribute(new Attribute("test", "page != null"));
        localXmlElement5.addElement(new TextElement("<![CDATA[ ) row_ ) where rownum_ > #{page.begin} and rownum_ <= #{page.end} ]]>"));
        localXmlElement4.addElement(localXmlElement5);
        localXmlElement1.addElement(localXmlElement4);
        return super.sqlMapDocumentGenerated(paramDocument, paramIntrospectedTable);
    }

    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement paramXmlElement, IntrospectedTable paramIntrospectedTable)
    {
        XmlElement localXmlElement1 = new XmlElement("include");
        localXmlElement1.addAttribute(new Attribute("refid", "OracleDialectPrefix"));
        paramXmlElement.getElements().add(0, localXmlElement1);
        XmlElement localXmlElement2 = new XmlElement("include");
        localXmlElement2.addAttribute(new Attribute("refid", "OracleDialectSuffix"));
        paramXmlElement.getElements().add(localXmlElement2);
        return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(paramXmlElement, paramIntrospectedTable);
    }

    private void addPage(TopLevelClass paramTopLevelClass, IntrospectedTable paramIntrospectedTable, String paramString)
    {
        paramTopLevelClass.addImportedType(new FullyQualifiedJavaType(paginationOrcl));
        CommentGenerator localCommentGenerator = this.context.getCommentGenerator();
        Field localField = new Field();
        localField.setVisibility(JavaVisibility.PROTECTED);
        localField.setType(new FullyQualifiedJavaType(paginationOrcl));
        localField.setName(paramString);
        localCommentGenerator.addFieldComment(localField, paramIntrospectedTable);
        paramTopLevelClass.addField(localField);
        char c = paramString.charAt(0);
        String str = Character.toUpperCase(c) + paramString.substring(1);
        Method localMethod = new Method();
        localMethod.setVisibility(JavaVisibility.PUBLIC);
        localMethod.setName("set" + str);
        localMethod.addParameter(new Parameter(new FullyQualifiedJavaType(paginationOrcl), paramString));
        localMethod.addBodyLine("this." + paramString + "=" + paramString + ";");
        localCommentGenerator.addGeneralMethodComment(localMethod, paramIntrospectedTable);
        paramTopLevelClass.addMethod(localMethod);
        localMethod = new Method();
        localMethod.setVisibility(JavaVisibility.PUBLIC);
        localMethod.setReturnType(new FullyQualifiedJavaType(paginationOrcl));
        localMethod.setName("get" + str);
        localMethod.addBodyLine("return " + paramString + ";");
        localCommentGenerator.addGeneralMethodComment(localMethod, paramIntrospectedTable);
        paramTopLevelClass.addMethod(localMethod);
    }

    public boolean validate(List<String> paramList)
    {
        return true;
    }
}
