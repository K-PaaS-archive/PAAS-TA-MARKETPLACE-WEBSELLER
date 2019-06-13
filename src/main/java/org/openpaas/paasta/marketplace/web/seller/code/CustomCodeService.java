package org.openpaas.paasta.marketplace.web.seller.code;

import org.openpaas.paasta.marketplace.web.seller.common.RestTemplateService;
import org.openpaas.paasta.marketplace.web.seller.common.SellerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

/**
 * Custom Code Service
 *
 * @author hrjin
 * @version 1.0
 * @since 2019-05-08
 */
@Service
public class CustomCodeService {

    @Autowired
    private RestTemplateService marketApiRest;

    /**
     * GroupCode 로 단위코드 목록 조회
     *
     * @param groupCode the group code
     * @return CustomCodeList
     */
    public CustomCodeList getUnitCodeListByGroupCode(String groupCode) {
    	return marketApiRest.send(SellerConstants.TARGET_API_MARKET, SellerConstants.URI_API_CUSTOM_CODE + "/groupCode/" + groupCode, null, HttpMethod.GET, null, CustomCodeList.class);
    }

}
