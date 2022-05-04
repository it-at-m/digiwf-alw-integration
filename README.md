## Customize this file after creating the new REPO and remove this lines.
What to adjust:  
* Add the your project or repo name direct under the logo.
* Add a short and long desciption.
* Add links for your final repo to report a bug or request a feature.
* Add list of used technologies.
* If you have, add a roadmap or remove this section.
* Fill up the section for set up and documentation.
 * Start in this file only with documentation and link to the docs folder.
* Add project shields. Use [shields.io](https://shields.io/)

## ------- end to remove -------
<div id="top"></div>

<!-- PROJECT SHIELDS -->

<!-- END OF PROJECT SHIELDS -->

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="#">
    <img src="/images/logo.png" alt="Logo" height="200">
  </a>

<h3 align="center">DigiWF ALW-Integration</h3>

  <p align="center">
    Spring-Boot-Starter project to integrate the ALW-System into DigiWF
    <br /><a href="#">Report Bug</a>
    ·
    <a href="#">Request Feature</a>
  </p>
</div>

<!-- ABOUT THE PROJECT -->
## About The Project

*TODO: Add a description from your project here.*
<p align="right">(<a href="#top">back to top</a>)</p>



### Built With

The documentation project is built with technologies we use in our projects:

* Spring-Boot
* Spring-Cloud-Stream
* Jackson?
* Jaxb
* http?

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- ROADMAP -->
## Roadmap

See the [open issues](#) for a full list of proposed features (and known issues).

<p align="right">(<a href="#top">back to top</a>)</p>

## Set up
* Go to the example-app
* Configure the application.yml

```
# KAFKA
spring.cloud.stream.kafka.binder.brokers: <THE URL TO YOUR KAFKA INSTANCE>
spring.cloud.stream.bindings.sendMessage-out-0.destination: <YOUR CUSTOM REQUEST TOPIC>
spring.cloud.stream.bindings.sendCorrelateMessage-out-0.destination: <YOUR CUSTOM RESPONSE TOPIC>
spring.cloud.stream.bindings.functionRouter-in-0.group: <YOUR GROUP>
spring.cloud.stream.bindings.functionRouter-in-0.destination: <YOUR CUSTOM REQUEST TOPIC> # For a roundtrip use the same value as in "spring.cloud.stream.bindings.sendMessage-out-0.destination" 
spring.kafka.ssl.key-store-location: <Resource path to your keystore>
spring.kafka.ssl.trust-store-location: <Resource path to your truststore>
spring.kafka.ssl.key-store-password: <Password to your keystore>
spring.kafka.ssl.trust-store-password: <Password to your truststore>
spring.kafka.ssl.key-password: <Key password>

# ALW
TODO
```
* Start the example application
* Make a http request to the configured test endpoints from <i>ExampleController</i>
* Observe the output in the console

<p align="right">(<a href="#top">back to top</a>)</p>

## Documentation
Detailed documentation see TODO (docs-Folder)

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please open an issue with the tag "enhancement", fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Open an issue with the tag "enhancement"
2. Fork the Project
3. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
4. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
5. Push to the Branch (`git push origin feature/AmazingFeature`)
6. Open a Pull Request

More about this in the [CODE_OF_CONDUCT](/CODE_OF_CONDUCT.md) file.

<p align="right">(<a href="#top">back to top</a>)</p>


<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE` file for more information.

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

it@m - opensource@muenchendigital.io

[join our slack channel](https://join.slack.com/t/digiwf/shared_invite/zt-14jxazj1j-jq0WNtXp7S7HAwJA7tKgpw)

<p align="right">(<a href="#top">back to top</a>)</p>


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
