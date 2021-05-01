package com.meng.blog.model;

import javax.persistence.*;




@Entity

public class RefreshToken {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private String token;
		private String createdDate;
		public RefreshToken(Long id, String token, String createdDate) {
			super();
			this.id = id;
			this.token = token;
			this.createdDate = createdDate;
		}
		public RefreshToken() {
			super();
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public String getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(String createdDate) {
			this.createdDate = createdDate;
		}
		
}
