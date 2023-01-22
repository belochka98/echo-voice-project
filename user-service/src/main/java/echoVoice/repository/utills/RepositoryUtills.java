package echoVoice.repository.utills;

import jakarta.persistence.Parameter;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class RepositoryUtills {
    // ToDo: logs of others actions in method
    public static void setQueryParametersByFilter(Query query, Object filter) {
        if (Objects.isNull(filter)) {
            return;
        }

        log.info("Trying to set parameters to query by " + filter.getClass().getSimpleName() + " class");
        final var params = query.getParameters().stream().map(Parameter::getName).collect(Collectors.toSet());
        log.info("Founded params " + params);

        Stream.of(filter.getClass().getDeclaredFields()).filter(f -> params.stream().anyMatch(s -> s.equals(f.getName()))).forEach(
                f -> {
                    f.setAccessible(true);

                    try {
                        log.info("Try set " + f.getName() + " parameter");
                        query.setParameter(f.getName(), f.get(filter));
                        log.info("Success to set " + f.getName() + " parameter");
                    } catch (IllegalAccessException e) {
                        log.error("Failed to set " + f.getName() + " parameter");
                    }

                    f.setAccessible(false);
                }
        );
    }
}
