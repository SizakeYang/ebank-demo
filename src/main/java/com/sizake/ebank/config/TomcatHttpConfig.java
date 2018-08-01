package com.sizake.ebank.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatHttpConfig {

    @Value("${from.server.http.port}")
    private Integer httpPort;

    @Value("${server.port}")
    private Integer httpsPort;

    /**
     * 配置内置的servlet容器工厂为tomcat.
     *
     * @return
     */
    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        final TomcatEmbeddedServletContainerFactory
                tomcat = new TomcatEmbeddedServletContainerFactory() {

            @Override
            protected void
            postProcessContext(final Context context) {

                final SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                final SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        //添加连接配置，主要是http的配置信息.
        tomcat.addAdditionalTomcatConnectors(this.initiateHttpConnector());


        tomcat.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {

//            //to http2,jdk9+tomcat9
//            connector.addUpgradeProtocol(new Http2Protocol());

            if ((connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>)) {
                //Tomcat large file upload connection reset
                //-1 means unlimited
                ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
            }

        });

        return tomcat;
    }

    /**
     * 配置一个http连接信息.
     */
    private Connector initiateHttpConnector() { // for RedirectPort
        final Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(this.httpPort);
        connector.setSecure(false);
        connector.setRedirectPort(this.httpsPort);// http 302
        return connector;
    }


}