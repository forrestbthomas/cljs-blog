(ns cljs-blog.core
  (:require
    [reagent.core :as r]
    [antizer.reagent :as ant]))

;; -------------------------
;; State
(defn about []
  [:div 
   [:p "My name is Forrest Thomas and I am a software engineer."]
   [:p "I got into software engineering because I love solving problems. I also got into software engineering because I care about people and making the world a better place. Personally, I am both a social and technical person and like combining those two things. We make software for people and I feel that software has the potential to effect both personal and societal growth and this is a major driving factor in my interest in it. I truly believe that technology can make our lives better and I want to be a part of that."]
   [:p "Please select a category in the sidebar to see my updated resume"]])

(def display (r/atom {:content (r/as-element [about])}))

;; -------------------------
;; Views
(defn skills []
  [ant/card {:title "Technical Skills"
             :cover (r/as-element [:img {:style {:width "25%"
                                                 :margin "0 auto"}
                                         :src "/images/tech-word-cloud.jpeg"}])
             :style {:background "#ECECEC"}}
   [ant/row {:gutter 16
             :type "flex"
             :justify "space-around"
             :style {:margin-bottom "16px"}}
    [ant/col {:span 8}
     [ant/card {:title "AWS"
                :style {:height "100%"}} "S3, CloudFront, Route53, EC2, ELB, CloudFormation, AutoScaling, CloudWatch, SNS, API Gateway, Lambda"]]
    [ant/col {:span 8}
     [ant/card {:title "Clojure/Onyx"
                :style {:height "100%"}} "Stream Processing Application Utilizing Kafka, Zookeeper and Onyx"]]
    [ant/col {:span 8}
     [ant/card {:title "NodeJS"
                :style {:height "100%"}} "Proxy Servers, Web Servers, and Build Servers for Delivery Pipelines and Front-End Web Applications"]]]
   [ant/row {:gutter 16
             :type "flex"
             :justify "space-around"
             :style {:margin-bottom "16px"}}
    [ant/col {:span 8}
     [ant/card {:title "Javascript"
                :style {:height "100%"} } "Dynamic Web Applications in AngularJS (1 and 2+), Ember and Backbone"]]
    [ant/col {:span 8}
     [ant/card {:title "Kubernetes/Docker"
                :style {:height "100%"}} "Containerization for Process Isolation and Multi-Tenancy in a PaaS Architecture"]]]])

(defn experience []
  [ant/card {:title "Experience"
             :cover (r/as-element [:img {:style {:width "25%"
                                                 :margin "0 auto"}
                                         :src "/images/experience.png"}])
             :style {:background "#ECECEC"}}
   [ant/card {:title "Capital One - Senior Software Engineer"
              :style {:margin-bottom "16px"}} 
    [:ul
     [:li "Spearheaded development of GitOps based CI/CD platform, integrated with internal governance processes to enable innovation and ensure compliance"]
     [:li "Developed a stream processing application for analyzing the health of mission critical systems and determining transitive effect of individual system health in the dependency tree"]
     [:li "Managed a team of 4 software engineers charged with delivering the web application platform and deployment infrastructure leveraged for hundreds of capitalone.com web pages"]
     [:li "Created the internal front-end framework used to build pre-authenticated capitalone.com web pages, including the capitalone.com homepage, which attracts 60 million page views per month"]
     [:li "Architected the internal deployment platform responsible for building, testing and delivering capitalone.com web pages to millions of customers with the push of a button"]]]
   [ant/card {:title "Monsoon Company - Web Developer"
              :style {:margin-bottom "16px"}} 
    [:ul
     [:li "Engineered a full stack web application for Bloomberg, used to track and identify potential trading violations"]
     [:li "Built a network graph visualization tool for Bloomberg, used to display relationships between individual traders and their internal/external communication lines"]
     [:li "Developed on a full stack web application for tax offices to manage their customer relationships, profiles, and tax documents"]]]
   [ant/card {:title "SCC - Counseling"
              :style {:margin-bottom "16px"}} 
    [:ul
     [:li "Counseled hundreds of individuals and couples and facilitated conflict resolution"]
     [:li "Leveraged community resources to work with individuals and families in need"]
     [:li "Communicated effectively at speaking engagements with audiences up to 500"]
     [:li "Managed a productive staff and volunteer team of 20 to accomplish program goals"]]]])

(defn education []
  [ant/card {:title "Education"
             :cover (r/as-element [:img {:style {:width "25%"
                                                 :margin "0 auto"}
                                         :src "/images/education.jpg"}])
             :style {:background "#ECECEC"}}
   [ant/row {:gutter 16}
    [ant/col {:span 8}
     [ant/card {:title "University of Edinburgh"}
      [:p "Master of Theology - Theology in History"]
      [:p "Graduated 2013"]]]
    [ant/col {:span 8}
     [ant/card {:title "Lincoln Seminary"}
      [:p "Master of Arts - Pastoral Counseling"]
      [:p "Graduated 2010"]]]
    [ant/col {:span 8}
     [ant/card {:title "William Jessup University"}
      [:p "Bachelor of Arts - Theology"]
      [:p "Graduated 2006"]]]]])

(defn certs []
  [ant/card {:title "Certifications"
             :cover (r/as-element [:img {:style {:width "25%"
                                                 :margin "0 auto"}
                                         :src "/images/certifications.jpg"}])
             :style {:background "#ECECEC"}}
   [ant/row {:gutter 16}
    [ant/col {:span 8}
     [ant/card {:title "AWS Solutions Architect - Associate"}
      [:p "2016 - 2019"]]]
    [ant/col {:span 8}
     [ant/card {:title "AWS SysOps Administrator - Associate"}
      [:p "2017 - 2020"]]]
    [ant/col {:span 8}
     [ant/card {:title "Certified SCRUM Master - SCRUM Alliance"}
      [:p "2016 - 2019"]]]]])

(defn sidebar []
  [ant/layout-sider 
   [:div {:style {:color "#fff"}} "FORRESTBTHOMAS.COM"]
   [ant/menu {:theme "dark"
              :style { :height "100vh" }
              :defaultSelectedKeys ["0"]}
    [ant/menu-item {:key 0
                    :on-click #(reset! display {:content [about]})} "About"]
    [ant/menu-item {:key 1
                    :on-click #(reset! display {:content [skills]})} "Technical Skills"]
    [ant/menu-item {:key 2
                    :on-click #(reset! display {:content [experience]})} "Experience"]
    [ant/menu-item {:key 3
                    :on-click #(reset! display {:content [education]})} "Education"]
    [ant/menu-item {:key 4
                    :on-click #(reset! display {:content [certs]})} "Certifications"]]])


(defn home-page []
  [ant/layout
   [sidebar]
   [ant/layout
    [ant/layout-content (:content @display)]]])


;; -------------------------
;; Initialize app

(defn mount-root []
  (r/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
