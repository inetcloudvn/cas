package org.apereo.cas.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apereo.cas.authentication.principal.Principal;
import org.apereo.cas.authentication.principal.Service;
import org.apereo.cas.util.ScriptingUtils;
import lombok.Getter;

/**
 * This is {@link ScriptedRegisteredServiceUsernameProvider}.
 *
 * @author Misagh Moayyed
 * @since 5.2.0
 */
@Slf4j
@Getter
public class ScriptedRegisteredServiceUsernameProvider extends BaseRegisteredServiceUsernameAttributeProvider {

    private static final long serialVersionUID = -678554831202936052L;

    private String script;

    public ScriptedRegisteredServiceUsernameProvider() {
    }

    public ScriptedRegisteredServiceUsernameProvider(final String script) {
        this.script = script;
    }

    @Override
    protected String resolveUsernameInternal(final Principal principal, final Service service, final RegisteredService registeredService) {
        try {
            LOGGER.debug("Found groovy script to execute");
            final Object result = ScriptingUtils.executeGroovyScriptEngine(this.script,
                new Object[]{principal.getAttributes(), principal.getId(), LOGGER}, Object.class);
            if (result != null) {
                LOGGER.debug("Found username [{}] from script [{}]", result, this.script);
                return result.toString();
            }
        } catch (final Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        LOGGER.warn("Script [{}] returned no value for username attribute. Fallback to default [{}]", this.script, principal.getId());
        return principal.getId();
    }

    public void setScript(final String script) {
        this.script = script;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        final ScriptedRegisteredServiceUsernameProvider rhs = (ScriptedRegisteredServiceUsernameProvider) obj;
        return new EqualsBuilder().appendSuper(super.equals(obj)).append(this.script, rhs.script).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().appendSuper(super.hashCode()).append(script).toHashCode();
    }
}
