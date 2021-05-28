package spring5_rest_study.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer{
    /** DispatcherServlet의 매핑경로를 '/'주었을 때, JSP/HTML/CSS 등을 올바르게 처리하기 위한 설정 추가 */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /** JSP를 통해서 컨트롤러의 실행 결과를 보여주기 위한 설정 */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/view/", ".jsp");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/memberlist").setViewName("/member/list");
        registry.addViewController("/read").setViewName("/member/get");
        registry.addViewController("/update").setViewName("/member/update");
        registry.addViewController("/registerMember").setViewName("/member/register");
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder
            .json()
            .featuresToEnable(SerializationFeature.INDENT_OUTPUT)
            .serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(formatter))
            .simpleDateFormat("yyyy-MM-dd HH:mm:ss")
            .build();
        converters.add(0, new MappingJackson2HttpMessageConverter(objectMapper));
    }
    
}
