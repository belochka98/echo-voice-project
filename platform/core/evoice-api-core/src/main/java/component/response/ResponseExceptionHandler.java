package component.response;

import dto.response.ApiError;
import enm.response.LoggingLevel;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@ControllerAdvice
@NoArgsConstructor
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseExceptionHandler.class);
    private static final Map<HttpStatus.Series, LoggingLevel> LOGGING_LEVEL_MAP = Map.of(
            HttpStatus.Series.INFORMATIONAL, LoggingLevel.INFO,
            HttpStatus.Series.SUCCESSFUL, LoggingLevel.INFO,
            HttpStatus.Series.REDIRECTION, LoggingLevel.INFO,
            HttpStatus.Series.CLIENT_ERROR, LoggingLevel.WARN,
            HttpStatus.Series.SERVER_ERROR, LoggingLevel.ERROR
    );
    private static final Map<Class<? extends Exception>, HttpStatus> EXCEPTION_STATUS_MAP = Map.of(
    );

    protected ResponseEntity<Object> buildResponseEntity(HttpStatus httpStatus, Throwable ex, String message) {
        final var apiError = ApiError.builder()
                .id(UUID.randomUUID())
                .status(httpStatus)
                .timestamp(LocalDateTime.now())
                .message(message)
                .debugMessage(ex.getLocalizedMessage())
                .stackTrace(Arrays.toString(ex.getStackTrace()))
                .build();

        this.log(httpStatus, ex, apiError.getId());

        return new ResponseEntity(apiError, apiError.getStatus());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception ex) {
        return Optional.ofNullable(AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class)).map(
                status -> this.buildResponseEntity(status.code(), ex, status.reason())
        ).orElseGet(
                () -> this.buildResponseEntity(
                        Objects.requireNonNullElse(EXCEPTION_STATUS_MAP.get(ex.getClass()), HttpStatus.INTERNAL_SERVER_ERROR),
                        ex,
                        ex.getMessage()
                )
        );
    }

    private void log(HttpStatus httpStatus, Throwable ex, UUID messageUid) {
        switch (LOGGING_LEVEL_MAP.get(httpStatus.series())) {
            case INFO -> LOGGER.info(String.valueOf(messageUid), ex);
            case WARN -> LOGGER.warn(String.valueOf(messageUid), ex);
            case ERROR -> LOGGER.error(String.valueOf(messageUid), ex);
            default ->
                    LOGGER.error(String.format("Non-cataloged type of error id=%s. See service core lib implementation", messageUid), ex);
        }
    }
}