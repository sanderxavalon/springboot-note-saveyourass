package org.iii.contoller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

// @RestController is a stereotype annotation that combines @ResponseBody and @Controller.

@RestController
@RequestMapping("/prefix")
public class RestfulController {

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public String getMethod(){
        return "success";
    }

    @RequestMapping(path = "/get/*", method = RequestMethod.GET)
    public String getMethodWithMultiPath(){
        return "success";
    }

    // 用瀏覽器打開看是否會以PDF形式開啟
    @RequestMapping(path = "/getPDF", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public String getMethodProduceJSON(){
        return "success";
    }

    // 刪除consumes後觀察Response Header會有什麼改變
    @RequestMapping(path = "/sendJSON", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String getMethodConsumeJSON(){
        return "success";
    }

    // 用postman的Body中改變content-type會有甚麼改變
    @RequestMapping(path = "/sendPlainText", method = RequestMethod.GET, headers = "content-type=text/plain")
    public String getMethodContentType(){
        return "success";
    }

    // 限定parameters在某種特定情況下才接收
    // 注意params內的內容不可空白分隔，如同URL傳過來的一樣
    @RequestMapping(path = "/specifyParameters", method = RequestMethod.GET, params = "id=100")
    public String getMethodSpecifyParameters(@RequestParam("id") String id){
        return "success";
    }

}
