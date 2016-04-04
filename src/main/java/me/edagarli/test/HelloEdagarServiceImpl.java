package me.edagarli.test;

import me.edagarli.test.service.EdagarliGithubService;
import me.edagarli.test.service.HelloEdagarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: edagarli
 * Email: lizhi@edagarli.com
 * Date: 16/4/4
 * Time: 10:34
 * Desc:
 */
@Service
public class HelloEdagarServiceImpl implements HelloEdagarService {

    @Override
    public String hello() {
        String msg = "";
        for (int i = 0; i < 1000000; i++) {

        }

        msg += "hello,edagarli(" + service.address() + "),Project address: " + service.projectAddress();

        return msg;
    }

    @Override
    public String playLOL() {
        return " come on and play lol";
    }

    @Autowired
    private EdagarliGithubService service;
}
