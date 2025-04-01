package com.company.jmixpmsecurity;

import com.company.jmixpmsecurity.app.RegistrationCleaner;
import com.google.common.base.Strings;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import org.quartz.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Push
@Theme(value = "jmix-pm-security")
@PWA(name = "Jmix Pm Security", shortName = "Jmix Pm Security")
@SpringBootApplication
public class JmixPmSecurityApplication implements AppShellConfigurator {

    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(JmixPmSecurityApplication.class, args);
    }

    @Bean
    @Primary
    @ConfigurationProperties("main.datasource")
    DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("main.datasource.hikari")
    DataSource dataSource(final DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @EventListener
    public void printApplicationUrl(final ApplicationStartedEvent event) {
        LoggerFactory.getLogger(JmixPmSecurityApplication.class).info("Application started at "
                + "http://localhost:"
                + environment.getProperty("local.server.port")
                + Strings.nullToEmpty(environment.getProperty("server.servlet.context-path")));
    }

    @Bean
    JobDetail registrationCleaningJob() {
        return JobBuilder.newJob(RegistrationCleaner.class)
                .withIdentity("registrationCleaning")
                .storeDurably() // Whether or not the Job should remain stored after it is orphaned (no Triggers point to it).
                .build();
    }

    @Bean
    Trigger registrationCleaningTrigger() {
        return TriggerBuilder.newTrigger()
                .withIdentity("registrationCleaningTrigger")
                .forJob(registrationCleaningJob())
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0 * * * * ?"))  // every minute at 00
                .build();
    }
}
