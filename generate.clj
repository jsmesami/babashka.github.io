#!/usr/bin/env bb

(require '[babashka.pods :as pods])
(pods/load-pod 'retrogradeorbit/bootleg "0.1.9")
(require '[clojure.edn :as edn]
         '[clojure.string :as s]
         '[pod.retrogradeorbit.bootleg.utils :as u]
)

(defn convert-to-html
  [site]
  (spit
   "index.html"
   (-> "data.edn"
       slurp
       edn/read-string
       site
       (u/convert-to :html))))
  

(defn site
  [data]
   [:html 
    [:head
     [:meta {:charset "UTF-8"}]
     [:title "Hello world"]
     [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0"}]
     [:link :rel "stylesheet" {:href "https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css"}]
     [:link {:href "https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" :rel "stylesheet"}]
     [:link {:href "https://fonts.gstatic.com" :rel "preconnect"}]
     [:link {:rel "stylesheet" :href "https://fonts.googleapis.com/css2?family=Forum&family=Raleway:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"}]
     ]
    [:body {:style "font-family: 'Raleway', sans-serif;"}
     [:header.w-full.shadow-md {:style "box-shadow: 0 4px 10px 0 rgba(0, 0, 0, 0.05);"}
      [:div.container.mx-auto.grid.grid-cols-2 {:style "height: 130px;"}
       [:h1.text-lg.my-auto 
        [:img {:src "./assets/logo.png"}]
        ]
       [:nav.flex.flex-row.flex-nowrap
        [:a.my-auto.mx-10 {:href "#"} "Overview"]
        [:a.my-auto.mx-10 {:href "#"} "Docs"]
        [:a.my-auto.mx-10 {:href "#"} "Contribute"]
        [:a.my-auto.mx-5 {:href "#"}
         [:img {:src "./assets/twitter.svg"}]]
        [:a.my-auto.mx-5 {:href "#"}
         [:img {:src "./assets/github.svg"}]]
        [:a.my-auto.mx-5 {:href "#"}
         [:img {:src "./assets/discord.svg"}]]
        [:a.my-auto.mx-5 {:href "#"}
         [:img {:src "./assets/youtube.svg"}]]
        ]]]
     [:main.px-80.pt-60
      [:h1.text-6xl {:style "font-family: Forum, serif;"} "Big words to describe,"]
      [:h1.text-6xl {:style "font-family: Forum, serif;"} "the project vision"]
      [:p.text-xl.mt-12 {:style "width: 600px;"} "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "]
      [:ul.container.mx-auto
       (for [{:keys [name location country]} (:people data)]
         [:li (s/join ", " [name location country])])]
      ]
    ]
   ])

(convert-to-html site)