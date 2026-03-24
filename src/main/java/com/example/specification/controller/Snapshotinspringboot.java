package com.example.specification.controller;

public class Snapshotinspringboot {

//    [INFO] \- org.springframework.boot:spring-boot-starter-test:jar:3.5.10-SNAPSHOT:test


//    ✅ Breakdown:
//
//    org.springframework.boot → group
//
//    spring-boot-starter-test → artifact
//
//3.5.10-SNAPSHOT → version (SNAPSHOT)
//
//    test → scope (used only for testing)

//
//    What It Means
//
//👉 You are using a development version of Spring Boot
//
//3.5.10-SNAPSHOT means:
//
//    Not officially released yet ❌
//
//    Still under development 🔧
//
//    Can change anytime 🔄
//
//            ⚠️ Why This Matters
//
//    Using SNAPSHOT version means:
//
//    You might get bugs or unstable behavior
//
//    Maven may download updates frequently
//
//    Build results may change without you updating code
//
//✅ Recommended Practice
//👉 Use stable version instead:
//<version>3.5.10</version>
//
//    or a known stable one like:
//
//<version>3.2.5</version>
//            🚀 When SNAPSHOT is OK
//
//    Use SNAPSHOT only if:
//
//    You are testing new features
//
//    You are contributing to Spring Boot
//
//    You explicitly need latest unreleased changes
//
//💡 Tip
//
//    To check what versions are available:\


//Simple Summary
//
//    SNAPSHOT = latest but unstable
//
//    no SNAPSHOT = stable release
//
//    Alright — let’s make this very simple and step-by-step 👍
//
//            🧠 1. What is a Version?
//
//    A version is just a number for your software.
//
//    Example:
//
//            1.0.0
//            2.1.3
//            3.5.10
//
//            👉 It tells:
//
//    Which stage the software is in
//
//    Whether it’s new / old / stable
//
//🔢 2. How version numbers work
//
//    Example:
//
//            1.2.3
//
//    Break it like this:
//
//    Part	Meaning
//1	Major (big changes)
//2	Minor (new features)
//3	Patch (bug fixes)
//🚀 3. What is SNAPSHOT?
//
//    Example:
//
//            1.0.0-SNAPSHOT
//
//👉 Means:
//
//            🛠️ "This version is still being built (not finished)"
//
//            📊 4. SNAPSHOT vs Normal Version
//    Version	Meaning
//1.0.0-SNAPSHOT	Work in progress 🚧
//            1.0.0	Final version ✅
//            🪜 5. Step-by-step lifecycle
//    Step 1 → Start development
//1.0.0-SNAPSHOT
//
//👉 Developers keep changing code
//
//    Step 2 → More updates
//
//    Still:
//
//            1.0.0-SNAPSHOT
//
//👉 Code improves again and again
//
//    Step 3 → Final release
//1.0.0
//
//        👉 Stable version (no SNAPSHOT)
//
//    Step 4 → Next update
//1.1.0-SNAPSHOT
//
//👉 New features being built
//
//🔁 6. Why SNAPSHOT is special
//
//    When Maven sees:
//
//            1.0.0-SNAPSHOT
//
//👉 It thinks:
//
//            "Let me check if there is a newer version"
//
//    So it may download updates again
//
//⚠️ 7. Important Rule
//
//👉 Use:
//
//    SNAPSHOT → during development
//
//    Normal version → in production
//
//💡 8. Real-life example
//
//    Think like this:
//
//            ✏️ SNAPSHOT = draft of your homework
//
//📄 FINAL = submitted homework
//
//🎯 Final Simple Summary
//
//            Version = software number
//
//            SNAPSHOT = not finished
//
//    No SNAPSHOT = finished & stable
//
//    SNAPSHOT keeps changing
//
//    Final version does NOT change
//
//    If you want, I can show you how Maven stores SNAPSHOT inside .m2 folder visually (very helpful to understand deeply).
//
//

//    409 usua
//    lly occuer in case of dup-lication

}
