package com.marveliu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Marveliu
 * @Date: 2018/11/19 2:54 PM
 * @Description:
 **/

@Configuration//开启配置
@EnableConfigurationProperties(FabricProperties.class)//开启使用映射实体对象
@ConditionalOnClass(FabricService.class)//存在FabricService时初始化该配置类
@ConditionalOnProperty//存在对应配置信息时初始化该配置类
        (
                prefix = "fabric",//存在配置前缀fabric
                value = "enabled",//开启
                matchIfMissing = true//缺失检查
        )
public class FabricAutoConfiguration {


    @Autowired
    private FabricProperties fabricProperties;

    /**
     * 根据条件判断不存在FabricService时初始化新bean到SpringIoc
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(FabricProperties.class)//缺失HelloService实体bean时，初始化HelloService并添加到SpringIoc
    public FabricService fabricService() {
        System.out.println(">>>The HelloService Not Found，Execute Create New Bean.");
        FabricService fabricService = new FabricService();
        // todo: 默认配置实现
        // helloService.setMsg(helloProperties.getMsg());//设置消息内容
        // helloService.setShow(helloProperties.isShow());//设置是否显示
        return fabricService;
    }
}
