@file:Suppress("unused", "unused")

package kr.peerfund.role.model

import com.fasterxml.jackson.annotation.JsonIgnore
import kr.peerfund.role.dto.RoleType
import kr.peerfund.user.model.User
import kr.peerfund.util.BaseEntity
import org.hibernate.annotations.DynamicInsert
import javax.persistence.*

@Entity
@DynamicInsert
@Table(name = "role")
class Role(
    @Column(name = "role_type", unique = true) @Enumerated(EnumType.STRING) var roleType: RoleType,
    @Column(name = "description") var description: String? = null,
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY) @JsonIgnore var users: MutableSet<User>? = null,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null
): BaseEntity()
