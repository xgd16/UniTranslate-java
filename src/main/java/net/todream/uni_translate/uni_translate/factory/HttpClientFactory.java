package net.todream.uni_translate.uni_translate.factory;

import java.time.Duration;

import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import reactor.netty.http.client.HttpClient;

@Component
public class HttpClientFactory {
    
    public WebClient httpClient(Duration timeout) {
        HttpClient httpClient = HttpClient.create();
        httpClient.responseTimeout(timeout);
        httpClient.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2000);

        return WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient)).build();
    }

}
