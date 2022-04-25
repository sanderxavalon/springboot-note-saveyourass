package com.torch.springbootspringmvcrestful.contoller;

import com.torch.springbootspringmvcrestful.valueobject.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@RestController
@RequestMapping("/prefix")
public class GetParametersController {

    // 1. HttpServletRequest直接拿參數
    @RequestMapping(path = "/OldFashionWay")
    public void getMethodHttpServletRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(request.getHeader("content-type"));
        System.out.println(request.getRequestURI());
        System.out.println(request.getParameter("id"));
        Writer writer = response.getWriter();
        writer.write("success");
        writer.flush();
        writer.close();
    }

    // 2. Method Parameter 對應 Request Parameter
    // 順序無關，如果無參嘖null
    @RequestMapping(path = "/getParamsWithoutAnnotation", method = RequestMethod.GET)
    public String getParamsWithoutAnnotation(String id, String user){
        return id + user;
    }
    // 3. RequestParam
    @RequestMapping(path = "/getParams", method = RequestMethod.GET)
    public String getMethodWithRequestParams(@RequestParam("id") String id){
        return id;
    }

    // RequestParam, 可以設定為參數非必填
    @RequestMapping(path = "/requiredParams", method = RequestMethod.GET)
    public String getMethodWithNotRequiredParams(@RequestParam(name = "id", required = false) String id){
        return id;
    }

    // RequestParam, 可以設定為參數非必填
    @RequestMapping(path = "/defaultValue", method = RequestMethod.GET)
    public String getMethodWithDefaultValue(@RequestParam(name = "id", defaultValue = "123") String id){
        return id;
    }

    // 4. Value Object直接映射
    @RequestMapping(path = "/getParamsReflectOnObject", method = RequestMethod.GET)
    public String getParamsReflectOnObject(User user){
        return user.getId()+ user.getName();
    }

    // 5. PathVariable, 試試看如果與上面"/get/*"路徑重疊會如何
    @RequestMapping(path = "/get/{id}/{number}", method = RequestMethod.GET)
    @ResponseBody
    public String getMethodDynamicURI(@PathVariable("id") String id, @PathVariable("number") int number){
        return id + number;
    }

    @RequestMapping(path = "/{action:test1|test2}", method = RequestMethod.GET)
    public String getMethodAntPathMatcher(@PathVariable("action") String action){
        return action;
    }
}
