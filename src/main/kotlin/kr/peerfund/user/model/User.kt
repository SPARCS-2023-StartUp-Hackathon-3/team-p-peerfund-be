@file:Suppress("unused", "unused")

package kr.peerfund.user.model

import com.fasterxml.jackson.annotation.JsonIgnore
import kr.peerfund.peerProjectUser.model.PeerProjectUser
import kr.peerfund.role.model.Role
import kr.peerfund.util.BaseEntity
import org.hibernate.annotations.DynamicInsert
import javax.persistence.*

@Entity
@DynamicInsert
@Table(name = "user")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(name = "user_name", unique = true, nullable = false)
    var userName: String,

    @Column(name = "password", nullable = false)
    @JsonIgnore
    var password: String,

    @Column(name = "name", nullable = false)
    var name: String,

    @OneToMany(mappedBy = "projectUser")
    var peerProjectList: MutableList<PeerProjectUser> = mutableListOf(),

    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.MERGE])
    @JoinTable(
        name = "user_role",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    var roles: Set<Role> = mutableSetOf()
): BaseEntity()

