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

    @Bean
    public Queue pronosticRequestQueue() { return new Queue("predict_match",false); }

    @Bean
    public Queue pronosticResultQueue() { return new Queue("predict_match_response", false); }

    @Bean
    public Queue tournamentRequestQueue() { return new Queue("predict_tournament", false); }

    @Bean
    public Queue tournamentResultQueue() { return new Queue("predict_tournament_response", false); }

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

}
