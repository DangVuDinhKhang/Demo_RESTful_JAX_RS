package com.appsdeveloperblog.app.ws.annotation;

import javax.ws.rs.NameBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@NameBinding                                        //https://raw.githubusercontent.com/DangVuDinhKhang/Note-for-Demo_RESTful_JAX_RS/main/Note%20(Comment%20code)
@Retention(RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Secured {
}
