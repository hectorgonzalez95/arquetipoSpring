package cl.hector.arqutipo_web.services;

import cl.hector.arqutipo_web.models.ArchivoAdjunto;
import cl.mineduc.framework2.utils.StringUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component("mailerSpringBootWebApp")
public class MailSenderService {
	private static final Logger log = LoggerFactory.getLogger(MailSenderService.class);

	@Autowired private JavaMailSender javaMailSender;
	@Autowired private Configuration configuration;

	public MimeMessage prepareMail(String mailTo, String nameTo, String mailContent, List<ArchivoAdjunto> adjuntos) throws MessagingException, UnsupportedEncodingException {
		log.info("CREANDO MAIL DE NOTIFICACION...");
		MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
		helper.setTo(new InternetAddress(mailTo,StringUtils.removeAccents(nameTo)));
		helper.setReplyTo(new InternetAddress("noreply@mineduc.cl","Notificación Mineduc"));
		helper.setFrom(new InternetAddress("noreply@mineduc.cl","Notificación Mineduc"));
		helper.setSubject("Notificación del Ministerio de Educación");
		
		for(ArchivoAdjunto adjunto : adjuntos) {
			log.debug("ATTACHING!...{}",adjunto.getNombreAdjunto());
			helper.addAttachment(adjunto.getNombreAdjunto(), new ByteArrayResource(adjunto.getAdjuntoByteArray()));
		}
		helper.setText(mailContent, true);
		mimeMessage = helper.getMimeMessage();
		log.info("MIME MULTIPART MESSAGE CREATED --> {}", helper.isMultipart());
		return mimeMessage;
	}

	public String parseMailTemplate(Object object) throws IOException, TemplateException {
		log.info("PREPARANDO TEMPLATE PARA MAIL DE NOTIFICACION...");
		Map<String, Object> notificacionMap = new HashMap<>();
		notificacionMap.put("nombre",StringUtils.removeAccents(StringUtils.removeAccents("Reemplazame con datos de objeto")));
		Template mailTemplate = configuration.getTemplate("mail/mail-notificacion-staticv2.ftl");
		String parsedTemplate = FreeMarkerTemplateUtils.processTemplateIntoString(mailTemplate, notificacionMap);
		log.info("TEMPLATE LISTO!");
		return parsedTemplate;
	}

	public void send(MimeMessage mail) {
		this.javaMailSender.send(mail);
	}
}