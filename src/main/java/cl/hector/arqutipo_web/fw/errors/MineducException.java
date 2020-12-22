package cl.hector.arqutipo_web.fw.errors;

import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.List;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class MineducException extends RuntimeException {

    Throwable rootCause;
    long code;
    String fullStackTrace;
    String simpleStackTrace;

    public MineducException(String message) {
        super("[" + UUID.randomUUID().toString() + "] - " + message);
    }

    public MineducException(String message, long errCode) {
        super("[" + UUID.randomUUID().toString() + "] - " + message);
        setCode(errCode);
    }

    public MineducException(String message, Throwable cause, long errCode) {
        super("[" + UUID.randomUUID().toString() + "] - " + message, cause);
        setCode(errCode);
        rootCause = ExceptionUtils.getRootCause(cause);
    }

    public MineducException(Exception cause) {
        super(cause);
        rootCause = ExceptionUtils.getRootCause(cause);
    }

    public MineducException(String message, Throwable cause) {
        super("[" + UUID.randomUUID().toString() + "] - " + message, cause);
        rootCause = ExceptionUtils.getRootCause(cause);
    }

    public static String printFullStackTrace(Throwable e) {
        return ExceptionUtils.getStackTrace(e);
    }

    // Getters
    public long getCode() {
        return code;
    }

    // Setters
    public void setCode(long v) {
        this.code = v;
    }

    public Throwable getRootCause() {
        return rootCause;
    }

    public String getFullStackTrace() {
        if (fullStackTrace == null) {
            fullStackTrace = ExceptionUtils.getStackTrace(this);
        }
        return fullStackTrace;
    }

    public String getSimpleStackTrace() {
        if (simpleStackTrace == null) {
            StringBuilder sb = new StringBuilder();
            List<Throwable> throwables = ExceptionUtils.getThrowableList(this);
            sb.append(ExceptionUtils.getMessage(throwables.remove(0)));
            for (Throwable thr : throwables) {
                sb.append(", caused by ");
                sb.append(ExceptionUtils.getMessage(thr));
            }
            simpleStackTrace = sb.toString();
        }
        return simpleStackTrace;
    }

    @Override
    public String toString() {
        String messageStr;

        messageStr = getMessage();
        if (messageStr == null) {
            messageStr = "";
        }
        if (getCode() > 0) {
            messageStr = messageStr + "[" + getCode() + "]";
        }
        if (getCause() != null) {
            if (getRootCause() != null) {
                messageStr = messageStr + ", Cause Exception: " + getCause() + ", Root Cause Exception: " + getRootCause();
            } else {
                messageStr = messageStr + ", Cause and Root Exception: " + getCause();
            }
        }
        return messageStr;
    }
}
