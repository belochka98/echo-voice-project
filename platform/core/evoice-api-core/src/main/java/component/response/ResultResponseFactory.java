package component.response;

import dto.response.ResponseError;
import dto.response.ResponseOk;
import dto.response.ResultResponse;
import enm.response.ResultCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ResultResponseFactory {
    public <T> ResultResponse<T> createResponse(ResultCode resultCode) {
        return new ResultResponse(resultCode);
    }

    public <T> ResultResponse<T> createResponse(ResultCode resultCode, T data) {
        return new ResultResponse(resultCode, data);
    }

    public <T> ResultResponse<T> createResponse(ResultCode resultCode, String resultText) {
        return new ResultResponse(resultCode, resultText);
    }

    public <T> ResultResponse<T> createResponse(ResultCode resultCode, String resultText, T data) {
        return new ResultResponse(resultCode, resultText, data);
    }

    public <T> ResponseOk<T> createResponseOk() {
        return new ResponseOk();
    }

    public <T> ResponseOk<T> createResponseOk(T data) {
        return new ResponseOk(data);
    }

    public <T> ResponseError<T> createResponseError() {
        return new ResponseError();
    }

    public <T> ResponseError<T> createResponseError(T data) {
        return new ResponseError(data);
    }
}
