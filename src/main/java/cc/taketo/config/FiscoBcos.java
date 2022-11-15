package cc.taketo.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.config.ConfigOption;
import org.fisco.bcos.sdk.config.exceptions.ConfigException;
import org.fisco.bcos.sdk.config.model.ConfigProperty;
import org.fisco.bcos.sdk.model.CryptoType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @Title: FiscoBcos
 * @Package: cc.taketo.config
 * @Description:
 * @Author: zhangp
 * @Date: 2022/11/15 - 10:01
 */
@Slf4j
@Data
@Component
public class FiscoBcos {

    @Autowired
    BcosConfig bcosConfig;

    BcosSDK bcosSDK;

    public void init() {
        ConfigProperty configProperty = loadProperty();
        ConfigOption configOption;
        try {
            configOption = new ConfigOption(configProperty, CryptoType.ECDSA_TYPE);
        } catch (ConfigException e) {
            log.error("init error:" + e.toString());
            return;
        }
        bcosSDK = new BcosSDK(configOption);
    }

    public ConfigProperty loadProperty() {
        ConfigProperty configProperty = new ConfigProperty();
        configProperty.setCryptoMaterial(bcosConfig.getCryptoMaterial());
        configProperty.setAccount(bcosConfig.getAccount());
        configProperty.setNetwork(new HashMap<String, Object>() {{
            put("peers", bcosConfig.getNetwork().get("peers"));
        }});
        configProperty.setAmop(bcosConfig.getAmop());
        configProperty.setThreadPool(bcosConfig.getThreadPool());
        return configProperty;
    }
}
