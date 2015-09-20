package com.yuncia.wechat.plugin;

import java.net.URL;
import java.util.List;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.ShellRunner;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.Context;

public class PaginationPlugin extends PluginAdapter
{
    public boolean modelExampleClassGenerated(TopLevelClass paramTopLevelClass, IntrospectedTable paramIntrospectedTable)
    {
        addLimit(paramTopLevelClass, paramIntrospectedTable, "limitStart");
        addLimit(paramTopLevelClass, paramIntrospectedTable, "limit");
        return super.modelExampleClassGenerated(paramTopLevelClass, paramIntrospectedTable);
    }

    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement paramXmlElement, IntrospectedTable paramIntrospectedTable)
    {
        XmlElement localXmlElement1 = (XmlElement)paramXmlElement.getElements().get(paramXmlElement.getElements().size() - 1);
        XmlElement localXmlElement2 = new XmlElement("if");
        localXmlElement2.addAttribute(new Attribute("test", "limitStart>=0 and limit>0"));
        localXmlElement2.addElement(new TextElement("limit ${limitStart} , ${limit}"));
        localXmlElement1.addElement(localXmlElement2);
        localXmlElement2 = new XmlElement("if");
        localXmlElement2.addAttribute(new Attribute("test", "limitStart==-1 and limit>0"));
        localXmlElement2.addElement(new TextElement("limit  ${limit}"));
        localXmlElement1.addElement(localXmlElement2);
        return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(paramXmlElement, paramIntrospectedTable);
    }

    private void addLimit(TopLevelClass paramTopLevelClass, IntrospectedTable paramIntrospectedTable, String paramString)
    {
        CommentGenerator localCommentGenerator = this.context.getCommentGenerator();
        Field localField = new Field();
        localField.setVisibility(JavaVisibility.PROTECTED);
        localField.setType(FullyQualifiedJavaType.getIntInstance());
        localField.setName(paramString);
        localField.setInitializationString("-1");
        localCommentGenerator.addFieldComment(localField, paramIntrospectedTable);
        paramTopLevelClass.addField(localField);
        char c = paramString.charAt(0);
        String str = Character.toUpperCase(c) + paramString.substring(1);
        Method localMethod = new Method();
        localMethod.setVisibility(JavaVisibility.PUBLIC);
        localMethod.setName("set" + str);
        localMethod.addParameter(new Parameter(FullyQualifiedJavaType.getIntInstance(), paramString));
        localMethod.addBodyLine("this." + paramString + "=" + paramString + ";");
        localCommentGenerator.addGeneralMethodComment(localMethod, paramIntrospectedTable);
        paramTopLevelClass.addMethod(localMethod);
        localMethod = new Method();
        localMethod.setVisibility(JavaVisibility.PUBLIC);
        localMethod.setReturnType(FullyQualifiedJavaType.getIntInstance());
        localMethod.setName("get" + str);
        localMethod.addBodyLine("return " + paramString + ";");
        localCommentGenerator.addGeneralMethodComment(localMethod, paramIntrospectedTable);
        paramTopLevelClass.addMethod(localMethod);
    }

    public boolean validate(List<String> paramList)
    {
        return true;
    }

    public static void generate()
    {
        String str = PaginationPlugin.class.getClassLoader().getResource("mybatisConfig.xml").getFile();
        String[] arrayOfString = { "-configfile", str, "-overwrite" };
        ShellRunner.main(arrayOfString);
    }

    public static void main(String[] paramArrayOfString)
    {
        generate();
    }
}
