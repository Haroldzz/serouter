package life.freebao.devops.serouter.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import life.freebao.devops.serouter.domain.Image;
import life.freebao.devops.serouter.domain.Message;
import life.freebao.devops.serouter.domain.Text;
import life.freebao.devops.serouter.domain.enumeration.MessageType;
import life.freebao.devops.serouter.page.router.InformationPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@Service
public class InformationService {
    private final Logger log = LoggerFactory.getLogger(InformationService.class);
    private final InformationPage informationPage;
    private final HttpClient httpClient;
    @Value("${application.wecom.router.token:9d44e914-27d2-6161-1c46-79767ef6379c}")
    private String token;

    public InformationService(InformationPage informationPage, HttpClient httpClient) {
        this.informationPage = informationPage;
        this.httpClient = httpClient;
    }

    public HashMap<String, String> getInformation() throws InterruptedException {
        return informationPage.getInformationPage();
    }

    public Message getMessage(Text text){
        Message.Builder builder = new Message.Builder();
        return builder.text(text).messageType(MessageType.text).build();
    }
    public Message getMessage(Image image){
        Message.Builder builder = new Message.Builder();
        return builder.image(image).messageType(MessageType.image).build();
    }
    public Map<String, String> sendMessage(String hookUrl, Message message) throws IOException, InterruptedException {
        String requestMessage = new ObjectMapper().writeValueAsString(message);
        log.debug("requestMessage = {}", requestMessage);
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(hookUrl)).POST(HttpRequest.BodyPublishers.ofString(requestMessage)).build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        Map<String, String> responseBodyMap = new ObjectMapper().readValue(httpResponse.body(), new TypeReference<Map<String, String>>(){});
        log.debug("responseBodyMap = {}", responseBodyMap);
        return responseBodyMap;
    }

    @Scheduled(cron = "${application.webdriver.router.information.text.cron:0 0 * * * ?}")
    public void sendRouterInformationTextToWecomTask() throws IOException, InterruptedException {
        String hookUrl = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=".concat(token);
        Text text = new Text.Builder().content(getInformation().get("text")).build();
        Message message = getMessage(text);
        log.debug("message = {}", message);
        Map<String, String> responseMap = sendMessage(hookUrl, message);
        log.info("responseMap = {}", responseMap);
    }
    @Scheduled(cron = "${application.webdriver.router.information.image.cron:0 0 * * * ?}")
    public void sendRouterInformationImageToWecomTask() throws IOException, InterruptedException {
        String hookUrl = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=".concat(token);
        HashMap<String, String> information = getInformation();
        Image image = new Image.Builder().md5(information.get("imageDigest")).base64(information.get("imageEncode")).build();
        Message message = getMessage(image);
        log.debug("message = {}", message);
        Map<String, String> responseMap = sendMessage(hookUrl, message);
        log.info("responseMap = {}", responseMap);
    }
}
