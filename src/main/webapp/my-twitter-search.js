
	function MyTwitter() {
		this.div_id = "";
		this.search = "";
		this.start = doTwitter;
	}
	var my_twitter;
	function doTwitter() {
		if (my_twitter != null) {
			my_twitter.destroy();
		}
			my_twitter = new TWTR.Widget({
			  version: 2,
			  type: 'search',
			  //id: this.div_id,
			  search: this.search,
			  interval: 10000,
			  title: 'Recent Posts',
			  subject: this.search,
			  width: 250,
			  height: 300,
			  theme: {
			    shell: {
			      background: '#ff0000',
			      color: '#ffffff'
			    },
			    tweets: {
			      background: '#ffffff',
			      color: '#444444',
			      links: '#1985b5'
			    }
			  },
			  features: {
			    scrollbar: true,
			    loop: true,
			    live: true,
			    behavior: 'default'
			  }
			}).render().start();
		
	}
	
	// Not used
	// Just another example how to use the TWTR widget
	function changeTwitter() {
		this.my_twitter
      .destroy()
      .setFeatures({
        live: true,
        scrollbar: false,
        behavior: 'default',
        loop: true
      })
      .setDimensions(250, 300)
      .setSearch('beer cheese')
      .setTweetInterval(10000)
      .setTitle('Beer')
      .setCaption('More Beer')
      .render().start();
	}

