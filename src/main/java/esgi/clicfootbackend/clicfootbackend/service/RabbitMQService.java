package esgi.clicfootbackend.clicfootbackend.service;

import esgi.clicfootbackend.clicfootbackend.Model.Player;
import esgi.clicfootbackend.clicfootbackend.Model.SearchResult;
import esgi.clicfootbackend.clicfootbackend.Model.Team;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {

    @Autowired
    private Queue playerSearchQueue;

    @Autowired
    private Queue teamSearchQueue;

    @Autowired
    private Queue playerStatsQueue;

    @Autowired
    private Queue teamStatsQueue;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MessageConverter jsonMessageConverter;

    public RabbitMQService(final RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setMessageConverter(jsonMessageConverter);
    }

    public SearchResult playerSendSearchRequest(String name){
        rabbitTemplate.setMessageConverter(jsonMessageConverter);
        SearchResult result = (SearchResult) rabbitTemplate.convertSendAndReceive(playerSearchQueue.getName(), name);
        return result;
    }

    @RabbitListener(queues = "player.search")
    public SearchResult playerSendSearchRequestListener(String name){
        System.out.println("Received request with : " + name);
        SearchResult result = new SearchResult();
        return result;
    }

    public SearchResult teamSendSearchRequest(String name){
        rabbitTemplate.setMessageConverter(jsonMessageConverter);
        SearchResult result = (SearchResult) rabbitTemplate.convertSendAndReceive(teamSearchQueue.getName(), name);
        return result;
    }

    @RabbitListener(queues = "team.search")
    public SearchResult teamSendSearchRequestListener(String name){
        System.out.println("Received request with : " + name);
        SearchResult result = new SearchResult();
        return result;
    }

    public Player playerSendPlayerStatsRequest(int id){
        rabbitTemplate.setMessageConverter(jsonMessageConverter);
        Player player = (Player) rabbitTemplate.convertSendAndReceive(playerStatsQueue.getName(), id);
        return player;
    }

    @RabbitListener(queues = "player.stats")
    public Player playerSendPlayerStatsRequestListener(String id){
        System.out.println("Received request with : " + id);
        Player player = new Player();
        return player;
    }

    public Team teamSendTeamStatsRequest(int id){
        rabbitTemplate.setMessageConverter(jsonMessageConverter);
        Team team = (Team) rabbitTemplate.convertSendAndReceive(teamStatsQueue.getName(), id);
        return team;
    }

    @RabbitListener(queues = "team.stats")
    public Team teamSendTeamStatsRequestListener(String id){
        Team team = new Team();
        return team;
    }

}
