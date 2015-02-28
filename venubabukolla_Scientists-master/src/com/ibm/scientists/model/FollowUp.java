package com.ibm.scientists.model;



public class FollowUp {
	
		
		private String name;
		private String image_url;
		
		
		public FollowUp(String name, String image_url) {
			super();
			this.name = name;
			this.image_url = image_url;
			
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getImage_url() {
			return image_url;
		}
		public void setImage_url(String image_url) {
			this.image_url = image_url;
		}
		
}
