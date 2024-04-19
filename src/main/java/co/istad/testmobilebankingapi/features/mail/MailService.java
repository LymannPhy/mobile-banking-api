package co.istad.testmobilebankingapi.features.mail;

import co.istad.testmobilebankingapi.features.mail.dto.MailRequest;
import co.istad.testmobilebankingapi.features.mail.dto.MailResponse;

public interface MailService {
    MailResponse sendMail(MailRequest mailRequest);
}
