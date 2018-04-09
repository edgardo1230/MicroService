package com.nearshoretechnology.focalpoint.common.dao;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan( basePackages = {"com.nearshoretechnology.focalpoint.api.*.model"} )
public class DaoConfig {

}
