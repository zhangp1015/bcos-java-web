package test;

import cc.taketo.contracts.HelloWorld;
import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.client.protocol.response.BlockNumber;
import org.fisco.bcos.sdk.config.ConfigOption;
import org.fisco.bcos.sdk.config.exceptions.ConfigException;
import org.fisco.bcos.sdk.config.model.ConfigProperty;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.CryptoType;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.springframework.boot.test.context.SpringBootTest;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.representer.Representer;

import java.io.InputStream;

/**
 * @Title: Test
 * @Package: test
 * @Description:
 * @Author: zhangp
 * @Date: 2022/11/14 - 15:49
 */

@SpringBootTest
public class Test {


    @org.junit.Test
    public void test1() throws ConfigException {
        Representer representer = new Representer();
        representer.getPropertyUtils().setSkipMissingProperties(true);
        Yaml yaml = new Yaml(representer);
        String configFile = "/fisco-config.yml";
        InputStream inputStream = this.getClass().getResourceAsStream(configFile);
        ConfigProperty configProperty = yaml.loadAs(inputStream, ConfigProperty.class);
        System.out.println(configProperty);
        ConfigOption configOption ;
        configOption = new ConfigOption(configProperty, CryptoType.ECDSA_TYPE);
        BcosSDK bcosSDK = new BcosSDK(configOption);
        System.out.println(bcosSDK.getClient(1).getBlockNumber().getBlockNumber());
    }
}
