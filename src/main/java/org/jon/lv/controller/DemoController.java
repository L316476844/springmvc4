package org.jon.lv.controller;

import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.jon.lv.annotation.UnLoginLimit;
import org.jon.lv.dto.TestDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @Package org.jon.lv.controller.DemoController
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/10 17:55
 * version V1.0.0
 */
@Api(tags = "DemoController", description = "demo")
@RestController
@RequestMapping(value = "/api/demo",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DemoController {

    @UnLoginLimit
    @ApiOperation(value = "免登陆例子", notes = "免登陆例子")
    @RequestMapping(value = "/unNeed/{version:.+}", method = RequestMethod.POST)
    public ResultDO<String> unNeed(
            @ApiParam(name="dto",value="免登陆例子", required=true)@RequestBody TestDTO dto,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){

        ResultDO<String> resultDO = new ResultDO<>();
        resultDO.setSuccess(true);
        resultDO.setData("请求成功");
        return resultDO;
    }

    @ApiOperation(value = "登陆例子", notes = "登陆例子")
    @RequestMapping(value = "/need/{version:.+}", method = RequestMethod.POST)
    public ResultDO<String> need(
            @ApiParam(name="dto",value="登陆例子", required=true)@RequestBody TestDTO dto,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){

        ResultDO<String> resultDO = new ResultDO<>();
        resultDO.setSuccess(true);

        return resultDO;
    }


}
