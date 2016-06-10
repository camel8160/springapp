package co.bandsportal.model.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="member_has_band") 
public class MemberHasBand implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@ManyToOne
	@JoinColumn(name="id_member")
	private Member member;
	
	@Id
	@ManyToOne
	@JoinColumn(name="id_band")
	private Band band;
	
	public MemberHasBand(){
		band = new Band();
		member = new Member();
	}

	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Band getBand() {
		return band;
	}
	public void setBand(Band band) {
		this.band = band;
	}	
	
}