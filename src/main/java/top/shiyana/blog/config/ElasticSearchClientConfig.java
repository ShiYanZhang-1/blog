package top.shiyana.blog.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName: elasticsearch
 * @Package: top.shiyana.elasticsearch.config
 * @ClassName: ElasticSearchClientConfig
 * @Author: dangerous
 * @Description:
 * @Date: 2020/4/24 13:25
 * @Version: 1.0
 */
@Configuration
public class ElasticSearchClientConfig {

    @Bean
    public RestHighLevelClient restHighLevelClient(){
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("127.0.0.1", 9200, "http")));
    }
}
