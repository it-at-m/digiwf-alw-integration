package io.muenchendigital.digiwf.alw.integration.api.controller;

import io.muenchendigital.digiwf.alw.integration.domain.exception.MissingInformationMailException;
import io.muenchendigital.digiwf.alw.integration.domain.model.Mail;
import io.muenchendigital.digiwf.alw.integration.domain.service.MailingService;
import io.muenchendigital.digiwf.spring.cloudstream.utils.api.streaming.service.PayloadSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ExampleController {

    private final MailingService mailingService;
    private final PayloadSenderService genericPayloadSender;

    @Value("${io.muenchendigital.email.test.receiver}")
    private String receiver;

    @GetMapping(value = "/testGetAlwPersonenInfo")
    public void testGetAlwPersonenInfo() {
        try {
            mailingService.sendMail(getMail());
        } catch (final MissingInformationMailException e) {
            log.error(e.toString());
        }
    }

    @GetMapping(value = "/testGetAlwPersonenInfoEventBus")
    public void testGetAlwPersonenInfoEventBus() {
        genericPayloadSender.sendPayload(getMail(), "getAlwPersonenInfoEventBus"); // send all messages to spring.cloud.stream.bindings.sendMessage-out-0.destination
    }

    private Mail getMail() {
        final Mail mail = new Mail();
        mail.setReceivers(receiver);
        mail.setSubject("Test1234");
        mail.setBody("Hallo test123");
        mail.setReplyTo("");
        return mail;
    }

}
