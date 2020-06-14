package esgi.clicfootbackend.clicfootbackend.configuration;

import com.rabbitmq.client.ConnectionFactory;
import lombok.var;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String TEST_EXCHANGE = "test.exchange";

    public static final String TEST_QUEUE = "test.queue";

    public static final String PLAYER_QUEUE = "player.queue";

    public static final String PLAYER_EXCHANGE = "player.exchange";

    public static final String TEAM_QUEUE = "team.queue";

    @Bean
    public Queue testQueue(){
        return new Queue(TEST_QUEUE, false);
    }

    @Bean
    public Queue playerSearchQueue(){
        return new Queue("player.search", false);
    }

    @Bean
    public Queue teamSearchQueue(){
        return new Queue("team.search", false);
    }

    @Bean
    public Queue playerStatsQueue(){
        return new Queue("player.stats", false);
    }

    @Bean
    public Queue teamStatsQueue(){
        return new Queue("team.stats", false);
    }

    @Bean
    public Queue teamQueue(){
        return new Queue(TEAM_QUEUE, false);
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

}
