//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import enm.response.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@Data
@AllArgsConstructor
public class ResultResponse<T> extends RepresentationModel {
    protected final ResultCode resultCode;
    protected String resultText;
    private T data;

    public ResultResponse() {
        this.resultCode = ResultCode.OK;
    }

    public ResultResponse(T data) {
        this();
        this.data = data;
    }

    public ResultResponse(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public ResultResponse(ResultCode resultCode, T data) {
        this(resultCode);
        this.data = data;
    }

    public ResultResponse(ResultCode resultCode, String resultText) {
        this(resultCode);
        this.resultText = resultText;
    }

    public boolean hasResult(ResultCode resultCode) {
        return this.resultCode.equals(resultCode);
    }
}
