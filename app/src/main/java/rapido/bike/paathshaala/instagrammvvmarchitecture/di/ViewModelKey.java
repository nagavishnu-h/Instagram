package rapido.bike.paathshaala.instagrammvvmarchitecture.di;

import androidx.lifecycle.ViewModel;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import dagger.MapKey;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@MapKey
public @interface ViewModelKey {
    /**
     * Value class.
     *
     * @return the class
     */
    Class<? extends ViewModel> value();
}