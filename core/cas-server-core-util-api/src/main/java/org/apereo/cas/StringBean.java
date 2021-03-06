package org.apereo.cas;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import java.io.Serializable;
import java.util.UUID;
import lombok.Getter;

/**
 * This is {@link StringBean}. Allows one to declare strings as Spring beans.
 *
 * @author Misagh Moayyed
 * @since 5.2.0
 */
@Slf4j
@Getter
public class StringBean implements Serializable {

    private static final long serialVersionUID = -2216572507148074902L;

    private String id;

    public StringBean(final String id) {
        this.id = id;
    }

    public StringBean() {
        this(UUID.randomUUID().toString());
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
        final StringBean rhs = (StringBean) obj;
        return new EqualsBuilder().append(this.id, rhs.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).toHashCode();
    }
}
