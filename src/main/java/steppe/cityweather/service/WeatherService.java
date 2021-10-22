package steppe.cityweather.service;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.regex.Pattern;

/**
 * Created by Алишер
 * On 22.10.2021 11:03
 */

@Service
public class WeatherService {
    private static final String GET_WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?q=";

    private static final String KEY = "ec59996a99d3d7ae037a3666e6953d07";

    public String getWeather(String city) {
        String result;
        if(Pattern.matches(".*\\p{InCyrillic}.*", city)){
            result = sendRequest(GET_WEATHER_URL + city + "&lang=ru&appid=" + KEY);
        } else {
            result = sendRequest(GET_WEATHER_URL + city + "&appid=" + KEY);
        }
        return result;
    }

    private String sendRequest(String url){
        Client client = ClientBuilder.newClient();

        WebTarget resource = client.target(url);

        Invocation.Builder request = resource.request();
        request.accept(MediaType.APPLICATION_JSON);

        Response response = request.get();

        if (response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
            return response.readEntity(String.class);
        } else {
            throw new RuntimeException("Error receiving data");
        }
    }
}
