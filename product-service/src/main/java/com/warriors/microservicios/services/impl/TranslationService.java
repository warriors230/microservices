package com.warriors.microservicios.services.impl;

import com.warriors.microservicios.services.ITranslationService;
import org.springframework.stereotype.Service;

import static com.warriors.microservicios.config.Translator.toLocale;
import static com.warriors.microservicios.utils.TranslationCode.SALUDOS;

@Service
public class TranslationService implements ITranslationService {

    @Override
    public String getTranslation() {
        return toLocale(SALUDOS);
    }
}
