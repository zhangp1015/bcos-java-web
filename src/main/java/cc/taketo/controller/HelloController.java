package cc.taketo.controller;

import cc.taketo.config.FiscoBcos;
import org.fisco.bcos.sdk.BcosSDK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

/**
 * @Title: TestController
 * @Package: cc.taketo.controller
 * @Description:
 * @Author: zhangp
 * @Date: 2022/11/14 - 15:47
 */

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    public FiscoBcos fiscoBcos;

    @GetMapping("/get")
    public String getBlockNumber(){
        fiscoBcos.init();
        BcosSDK bcosSDK = fiscoBcos.getBcosSDK();
        BigInteger blockNumber = bcosSDK.getClient(1).getBlockNumber().getBlockNumber();
        return blockNumber.toString();
    }
}
