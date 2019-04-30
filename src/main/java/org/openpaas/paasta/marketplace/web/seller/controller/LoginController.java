package org.openpaas.paasta.marketplace.web.seller.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

/**
 * 로그인 화면
 *
 * @author hrjin
 * @version 1.0
 * @since 2019-03-22
 */
@Controller
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);


    /**
     * Index page model and view.
     *
     * @return the model and view
     */
    @GetMapping(value = "/")
    public ModelAndView indexPage() {
        LOGGER.info("여기로 들어오면 성공!!! ");
        try{
            return new ModelAndView("redirect:/seller");
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


//    @GetMapping(value = "/market")
//    public ModelAndView main() {
//        // 고정된 Org 가 있는지 판별 후 그에 따라 화면 분기.
//        String fixedOrgName = marketOrgName;
//        LOGGER.info("market 이름 ::: " + fixedOrgName);
//        boolean result = orgService.isExistOrgByOrgName(fixedOrgName);
//        LOGGER.info("result 결과 ::: " + result);
//
//        ModelAndView mv = new ModelAndView();
//        if (result) {
//            mv.setViewName("/test/test");
//        } else {
//            mv.setViewName("/common/ready");
//        }
//        return mv;
//    }


    @GetMapping(value = "/seller")
    public ModelAndView main() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/test/test");
        return mv;
    }



    /**
     * 로그인 화면
     *
     * @param error   the error
     * @param logout  the logout
     * @param locale  the locale
     * @param request the request
     * @return ModelAndView model
     */
    @GetMapping(value = "/login")
    public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error,
                                  @RequestParam(value = "logout", required = false) String logout,
                                  @RequestParam(value = "code", required = false) String code,
                                  Locale locale, HttpServletRequest request) {

        ModelAndView model = new ModelAndView();

        if (error != null) {
            model.addObject("error", "Invalid login.");
        }

        if (logout != null) {
            model.addObject("message", "Logged out successfully.");
        }

        try{

            OAuth2Authentication auth2Authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
            List<GrantedAuthority> authorities = (List<GrantedAuthority>) auth2Authentication.getAuthorities();
            String loginUserRole = authorities.get(0).toString();
            LOGGER.info("loginUserRole : " + loginUserRole);

            if (!(loginUserRole.equalsIgnoreCase("ROLE_USER") || loginUserRole.equalsIgnoreCase("ROLE_ADMIN"))) {
                model.setViewName("/index");
            } else {
                model.setViewName("redirect:/seller");
            }
        }catch (Exception e) {
            e.printStackTrace();
            model.setViewName("/index");
        }

        return model;
    }



}
