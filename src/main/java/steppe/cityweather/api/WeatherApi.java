package steppe.cityweather.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import steppe.cityweather.service.WeatherService;

/**
 * Created by Алишер
 * On 22.10.2021 10:57
 */

@RestController()
@RequestMapping("/weather")
public class WeatherApi {
    @Autowired
    private WeatherService service;

    @GetMapping
    public String getWeather(@RequestParam String city) {
        return service.getWeather(city);
    }
}
