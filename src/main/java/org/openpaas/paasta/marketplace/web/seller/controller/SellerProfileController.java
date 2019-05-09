package org.openpaas.paasta.marketplace.web.seller.controller;

import org.openpaas.paasta.marketplace.web.seller.common.CommonService;
import org.openpaas.paasta.marketplace.web.seller.common.Constants;
import org.openpaas.paasta.marketplace.web.seller.model.SellerProfile;
import org.openpaas.paasta.marketplace.web.seller.service.SellerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 * 판매자 프로필 Controller
 *
 * @author hrjin
 * @version 1.0
 * @since 2019-05-07
 */

@RestController
public class SellerProfileController {

    private static final String VIEW_URL = "/profile";

    @Autowired
    CommonService commonService;

    @Autowired
    SellerProfileService sellerProfileService;

    /**
     * 프로필 등록 페이지 이동
     *
     * @param httpServletRequest the httpServletRequest
     * @return ModelAndView
     */
    @GetMapping(value = Constants.URI_SELLER_PROFILE)
    public ModelAndView getProfileCreatePage(HttpServletRequest httpServletRequest){
        return commonService.setPathVariables(httpServletRequest, VIEW_URL + "/createProfile", new ModelAndView());
    }

    /**
     * 프로필 등록
     *
     * @param sellerProfile the seller profile
     * @return SellerProfile
     */
    @PostMapping(value = Constants.URI_MARKET_API_PROFILE)
    private SellerProfile createProfile(@RequestBody SellerProfile sellerProfile){
        return sellerProfileService.createProfile(sellerProfile);
    }
}
