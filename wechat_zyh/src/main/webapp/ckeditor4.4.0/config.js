/**
 * @license Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
    config.language = 'zh-cn';
    config.uiColor = '#AADC6E'
    //config.skin = 'office2003';   ;
    config.image_previewText = '外联图片请直接输入图片地址。';//这里的内容自己可以定义。同样可以设置""空
    config.extraPlugins = 'uploadimage';//粘贴图片控件 /*/**/*/
    config.extraPlugins = 'uploadwidget';//粘贴图片控件 /*/**/*
    // /粘贴图片使用付费的simpleUploads插件或者使用applet插件。
/*    config.extraPlugins = 'dialogui';
    config.extraPlugins = 'dialog';
    config.extraPlugins = 'image';//粘贴图片控件*/
    //其他一些配置
    filebrowserBrowseUrl = '/ckfinder2.5.0/ckfinder.html';
    filebrowserImageBrowseUrl = '/ckfinder2.5.0/ckfinder.html?type=Images';
    filebrowserFlashBrowseUrl = '/ckfinder2.5.0/ckfinder.html?type=Flash';
    filebrowserUploadUrl = '/ckfinder2.5.0/core/connector/java/connector.java?command=QuickUpload&type=Files&currentFolder=/archive/';
    filebrowserImageUploadUrl = '/ckfinder2.5.0/core/connector/java/connector.java?command=QuickUpload&type=Images&currentFolder=/cars/';
    filebrowserFlashUploadUrl = '/ckfinder2.5.0/core/connector/java/connector.java?command=QuickUpload&type=Flash';

};
