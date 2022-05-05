package io.muenchendigital.digiwf.alw.integration.api.controller;

import io.muenchendigital.digiwf.alw.integration.domain.exception.AlwException;
import io.muenchendigital.digiwf.alw.integration.domain.model.AlwZustaendigkeitRequest;
import io.muenchendigital.digiwf.alw.integration.domain.service.AlwService;
import io.muenchendigital.digiwf.spring.cloudstream.utils.api.streaming.service.PayloadSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ExampleController {

    private final AlwService alwService;
    private final PayloadSenderService genericPayloadSender;

    @GetMapping(value = "/testGetAlwZustaendigkeit")
    public void testGetAlwZustaendigkeit() {
        try {
            alwService.getZustaendigkeit(createAlwZustaendigkeitRequest());
        } catch (final AlwException e) {
            log.error(e.toString());
        }
    }

    @GetMapping(value = "/testGetAlwZustaendigkeitEventBus")
    public void testGetAlwZustaendigkeitEventBus() {
        genericPayloadSender.sendPayload(createAlwZustaendigkeitRequest(), "getAlwZustaendigkeitEventBus"); // send all messages to spring.cloud.stream.bindings.sendMessage-out-0.destination
    }

    private AlwZustaendigkeitRequest createAlwZustaendigkeitRequest() {
        final AlwZustaendigkeitRequest alwZustaendigkeitRequest = new AlwZustaendigkeitRequest();
        alwZustaendigkeitRequest.setAzrNummer("test1234");
        return alwZustaendigkeitRequest;
    }

}
