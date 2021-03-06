/*
 * Copyright (c) 2019- 2019 threefish(https://gitee.com/threefish https://github.com/threefish) All Rights Reserved.
 * 本项目完全开源，商用完全免费。但请勿侵犯作者合法权益，如申请软著等。
 * 最后修改时间：2019/10/07 18:27:07
 * 源 码 地 址：https://gitee.com/threefish/NutzFw
 */

package com.nutzfw.core.plugin.view;

import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.ext.web.WebRender;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @author 黄川 huchuc@vip.qq.com
 * @date: 2018/12/26
 */
public class NutzFwWebRender extends WebRender {

    static final String INTERNAL_DATA_KEY = "data";

    HashMap<String, Object> data;

    public NutzFwWebRender(GroupTemplate gt) {
        super(gt);
    }

    public NutzFwWebRender(GroupTemplate gt, HashMap<String, Object> data) {
        super(gt);
        this.data = data;
    }

    /**
     * 可以添加更多的绑定
     *
     * @param template 模板
     * @param key      模板的资源id
     * @param request
     * @param response
     * @param args     调用render的时候传的参数
     */
    @Override
    protected void modifyTemplate(Template template, String key, HttpServletRequest request, HttpServletResponse response, Object... args) {
        Object object = args[0];
        //关闭静态类型检查，设置为动态变量类型 dynamic
        if (null == object && null != this.data) {
            template.binding(INTERNAL_DATA_KEY, this.data);
        } else {
            template.binding(INTERNAL_DATA_KEY, object);
        }
        template.binding("productVersion", BeetlViewMaker.isDev ? System.currentTimeMillis() : BeetlViewMaker.productVersion);
    }
}
