package org.apereo.cas.adaptors.yubikey;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.ToString;
import lombok.Getter;

/**
 * This is {@link YubiKeyAccount}.
 *
 * @author Misagh Moayyed
 * @since 5.2.0
 */
@Entity
@Table(name = "YubiKeyAccount")
@Slf4j
@ToString
@Getter
public class YubiKeyAccount {

    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id = -1;

    @Column(length = 255, updatable = true, insertable = true, nullable = false)
    private String publicId;

    @Column(length = 255, updatable = true, insertable = true, nullable = false)
    private String username;

    public YubiKeyAccount() {
        this.id = System.currentTimeMillis();
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public void setPublicId(final String publicId) {
        this.publicId = publicId;
    }

    public void setUsername(final String username) {
        this.username = username;
    }
}
