package co.istad.testmobilebankingapi.features.mail;

import co.istad.testmobilebankingapi.features.mail.dto.MailRequest;
import co.istad.testmobilebankingapi.features.mail.dto.MailResponse;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mails")
@RequiredArgsConstructor
public class MailController {
    private final MailService mailService;
    @PostMapping
    MailResponse send(@Valid @RequestBody MailRequest mailRequest) throws MessagingException {
        return mailService.sendMail(mailRequest);
    }
}
