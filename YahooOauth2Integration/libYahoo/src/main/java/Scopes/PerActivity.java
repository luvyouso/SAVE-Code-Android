package Scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by rahulkumar on 03/11/16.
 */

@javax.inject.Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {}
